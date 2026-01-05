package org.training.jobs;

import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import org.apache.log4j.Logger;
import org.training.service.TokenService;

public class GenerateTokenJob extends AbstractJobPerformable<CronJobModel> {

    private static final Logger LOG = Logger.getLogger(GenerateTokenJob.class);

    private final TokenService tokenService;

    public GenerateTokenJob(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public PerformResult perform(final CronJobModel cronJobModel) {
        try {
            LOG.info("Starting token generation job");

            tokenService.generateNewToken();

            LOG.info("Token successfully generated");

            return new PerformResult(
                    CronJobResult.SUCCESS,
                    CronJobStatus.FINISHED
            );
        } catch (Exception e) {
            LOG.error("Error while generating token", e);

            return new PerformResult(
                    CronJobResult.FAILURE,
                    CronJobStatus.ABORTED
            );
        }
    }
}
