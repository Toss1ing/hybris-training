package org.training.interceptors;

import de.hybris.platform.servicelayer.event.EventService;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.interceptor.PrepareInterceptor;
import de.hybris.platform.servicelayer.interceptor.ValidateInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.training.events.BandAlbumSalesEvent;
import org.training.model.BandModel;

import static de.hybris.platform.servicelayer.model.ModelContextUtils.getItemModelContext;

public class BandAlbumSalesInterceptor implements ValidateInterceptor, PrepareInterceptor {
    private static final long BIG_SALES = 50000L;
    private static final long NEGATIVE_SALES = 0L;

    @Autowired
    private EventService eventService;

    @Override
    public void onValidate(
            Object model,
            InterceptorContext ctx) throws InterceptorException {
        if (model instanceof BandModel band) {
            Long sales = band.getAlbumSales();
            if (sales != null && sales < NEGATIVE_SALES) {
                throw new InterceptorException("Album sales must be positive");
            }
        }
    }

    @Override
    public void onPrepare(final Object model, final InterceptorContext ctx)
            throws InterceptorException {
        if (model instanceof BandModel band) {
            if (hasBecomeBig(band, ctx)) {
                eventService.publishEvent(new BandAlbumSalesEvent(band.getCode(), band.getName(), band.getAlbumSales()));
            }
        }
    }

    private boolean hasBecomeBig(final BandModel band, final InterceptorContext ctx) {
        Long sales = band.getAlbumSales();
        if (sales != null && sales >= BIG_SALES) {
            if (ctx.isNew(band)) {
                return true;
            } else {
                Long oldValue = getItemModelContext(band).getOriginalValue(BandModel.ALBUMSALES);
                return oldValue == null || oldValue.intValue() < BIG_SALES;
            }
        }
        return false;
    }
}