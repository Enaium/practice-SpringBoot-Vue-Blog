package cn.enaium.misc.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Enaium
 */
@Data
@AllArgsConstructor
public class Result<T> {
    private int code;
    private String msg;
    private T data;
}
