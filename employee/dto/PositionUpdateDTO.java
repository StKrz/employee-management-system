package gr.aueb.cf.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PositionUpdateDTO extends BasePositionDTO {
    private String positionName;
}
