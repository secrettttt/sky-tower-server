package com.skytower.entry;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ActionEventGroupEntry {
    private String key;
    private List<LabelCountEntry> value;
}
