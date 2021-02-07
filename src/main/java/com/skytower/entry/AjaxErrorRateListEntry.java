package com.skytower.entry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AjaxErrorRateListEntry {
    private String api;
    private int success_count;
    private int error_count;
    private double ajax_error_rate;
}
