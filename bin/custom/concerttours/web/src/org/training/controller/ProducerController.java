package org.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.data.ProducerData;
import org.training.facades.ProducerFacade;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
public class ProducerController {

    private static final String PRODUCER_LIST_PAGE = "producerList";
    private static final String PRODUCER_DETAILS_PAGE = "producerDetails";

    private static final String PRODUCERS_ATTRIBUTE = "producers";
    private static final String PRODUCER_ATTRIBUTE = "producer";

    private static final String ENCODING = "UTF-8";

    private final ProducerFacade producerFacade;

    public ProducerController(ProducerFacade producerFacade) {
        this.producerFacade = producerFacade;
    }

    @RequestMapping(value = "/producers")
    public String showProducers(final Model model) {
        List<ProducerData> producerData = producerFacade.getProducers();
        model.addAttribute(PRODUCERS_ATTRIBUTE, producerData);

        return PRODUCER_LIST_PAGE;
    }

    @RequestMapping(value = "/producers/{producerId}")
    public String showProducerDetails(
            @PathVariable final String producerId,
            final Model model
    ) throws UnsupportedEncodingException {
        String decodedBandId = URLDecoder.decode(producerId, ENCODING);

        ProducerData producerData = producerFacade.getProducerByCode(decodedBandId);
        model.addAttribute(PRODUCER_ATTRIBUTE, producerData);

        return PRODUCER_DETAILS_PAGE;
    }

}
