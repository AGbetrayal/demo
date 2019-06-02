package com.core.commons.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author AGbetrayal
 * @date 2019/6/2 21:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TextValueItem implements Serializable {

    private String text;

    private Object value;

}