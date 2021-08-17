package hu.volgyvaros.helper;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class BuildInformation {

    private String version;

    @JsonFormat(pattern = "yyyy. MM. dd. HH:mm")
    private Date buildTime;

}
