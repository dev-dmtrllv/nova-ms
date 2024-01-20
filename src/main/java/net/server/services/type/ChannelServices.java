package net.server.services.type;

import net.server.services.BaseService;
import net.server.services.Service;
import net.server.services.ServiceType;
import net.server.services.task.channel.*;
/**
 * @author Ronan
 */
public enum ChannelServices implements ServiceType {

    MOB_STATUS(MobStatusService.class),
    MOB_ANIMATION(MobAnimationService.class),
    MOB_CLEAR_SKILL(MobClearSkillService.class),
    MOB_MIST(MobMistService.class),
    EVENT(EventService.class),
    OVERALL(OverallService.class);

    private final Class<? extends BaseService> s;

    ChannelServices(Class<? extends BaseService> service) {
        s = service;
    }

    @Override
    public Service<? extends BaseService> createService() {
        return new Service(s);
    }

    @Override
    public ChannelServices[] enumValues() {
        return ChannelServices.values();
    }

}
