package com.github.navelogic.api.Service.Model.Production;

import com.github.navelogic.api.Enum.ProductionStatusEnum;
import org.springframework.stereotype.Service;

@Service
public class AdvanceStatusService {
    public void advanceStatus(ProductionStatusEnum currentStatus) {
        switch (currentStatus) {
            case CONCEPT:
                currentStatus = ProductionStatusEnum.PRE_PRODUCTION;
                break;
            case PRE_PRODUCTION:
                currentStatus = ProductionStatusEnum.PRODUCTION;
                break;
            case PRODUCTION:
                currentStatus = ProductionStatusEnum.POST_PRODUCTION;
                break;
            case POST_PRODUCTION:
                currentStatus = ProductionStatusEnum.READY_FOR_RELEASE;
                break;
            case READY_FOR_RELEASE:
                currentStatus = ProductionStatusEnum.RELEASED;
                break;
            default:
                throw new IllegalArgumentException("Unknown production status: " + currentStatus);
        }
    }
}
