package org.training.service.impl;

import de.hybris.platform.servicelayer.model.ModelService;
import org.training.daos.TokenDAO;
import org.training.model.TokenItemModel;
import org.training.service.TokenService;

import java.util.UUID;

public class DefaultTokenService implements TokenService {

    private final ModelService modelService;
    private final TokenDAO tokenDAO;

    public DefaultTokenService(
            ModelService modelService,
            TokenDAO tokenDAO
    ) {
        this.modelService = modelService;
        this.tokenDAO = tokenDAO;
    }

    @Override
    public void generateNewToken() {
        TokenItemModel tokenItem = getOrCreateTokenItem();
        tokenItem.setToken(UUID.randomUUID().toString());
        modelService.save(tokenItem);
    }

    protected TokenItemModel getOrCreateTokenItem() {
        TokenItemModel tokenItem = tokenDAO.findTokenItem();

        if (tokenItem == null) {
            tokenItem = modelService.create(TokenItemModel.class);
        }

        return tokenItem;
    }
}

