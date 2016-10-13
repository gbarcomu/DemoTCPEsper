package esper;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

import model.BDT;

@Component
@Scope(value = "singleton")
public class OclusionEventHandler implements InitializingBean {

	private EPServiceProvider epService;
	private EPStatement criticalEventStatement;

	@Autowired
	@Qualifier("criticalEventSubscriber")
	private CriticalEventSubscriber criticalEventSubscriber;

	public void initService() {

		Configuration config = new Configuration();
		config.addEventTypeAutoName("es.quercus.sixtrems.event");
		epService = EPServiceProviderManager.getDefaultProvider(config);

		createCriticalOclusionCheckExpression();
	}

	private void createCriticalOclusionCheckExpression() {

		criticalEventStatement = epService.getEPAdministrator().createEPL(criticalEventSubscriber.getStatement());
		criticalEventStatement.setSubscriber(criticalEventSubscriber);
	}
	
    public void handle(BDT event) {

        epService.getEPRuntime().sendEvent(event);
    }
    
    public void afterPropertiesSet() {
        initService();
    }

}
