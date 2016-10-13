package esper;

import java.util.Map;
import org.springframework.stereotype.Component;
import model.BDT;

/**
 * Wraps Esper Statement and Listener. No dependency on Esper libraries.
 */
@Component
public class CriticalEventSubscriber {

    private static final String CRITICAL_EVENT_VALUE = "2";

    public String getStatement() {
        
        // Example using 'Match Recognise' syntax.
        String crtiticalEventExpression = "select * from TemperatureEvent "
                + "match_recognize ( "
                + "       measures A as bdt "
                + "       pattern (A) " 
                + "       define "
                + "               A as A.alertValue = " + CRITICAL_EVENT_VALUE + ")";
        
        return crtiticalEventExpression;
    }
    
    /**
     * Listener method called when Esper has detected a pattern match.
     */
    public void update(Map<String, BDT> eventMap) {

        BDT bdt = (BDT) eventMap.get("bdt");

        StringBuilder sb = new StringBuilder();
        sb.append("***************************************");
        sb.append("\n* [ALERT] : OCLUSION DETECTED! ");
        sb.append("\n* " + bdt );
        sb.append("\n***************************************");
    }

    
}
