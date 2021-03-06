package com.robertx22.mine_and_slash.commands.bases;

import com.robertx22.mine_and_slash.db_lists.registry.SlashRegistry;

import java.util.List;
import java.util.stream.Collectors;

public class RunewordSuggestions extends CommandSuggestions {

    @Override
    public List<String> suggestions() {
        return SlashRegistry.RuneWords()
                .getList()
                .stream()
                .map(x -> x.GUID())
                .collect(Collectors.toList());
    }

}

