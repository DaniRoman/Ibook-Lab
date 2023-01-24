package com.example.nttdatalab.services.impServices;

import com.example.nttdatalab.dto.BookRegistryDto;

public interface ISentLogService {

    public void publish(BookRegistryDto bookRegistryDto);
}
