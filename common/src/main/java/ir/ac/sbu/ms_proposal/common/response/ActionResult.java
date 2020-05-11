package ir.ac.sbu.ms_proposal.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ActionResult<T> {
    private boolean success;
    private String error;
    private T data;
}
