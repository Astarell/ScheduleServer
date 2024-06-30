package scheduleapp.scheduleserver.projection_views;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.beans.factory.annotation.Value;

@JsonPropertyOrder({"id", "pairTime"})
public interface TimepairView {
    Long getId();
    @Value("#{target.startPair + ' – ' + target.endPair}")
    String getPairTime();
//    String getStartPair();
//    String getEndPair();
}
