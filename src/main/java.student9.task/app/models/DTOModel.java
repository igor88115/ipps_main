package app.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DTOModel {
    private long id;
    private String name;
}
