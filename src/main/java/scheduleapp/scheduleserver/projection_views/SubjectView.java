package scheduleapp.scheduleserver.projection_views;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "name"})
public interface SubjectView {
    Long getId();
    String getName();
}
