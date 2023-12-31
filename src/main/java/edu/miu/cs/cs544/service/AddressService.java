package edu.miu.cs.cs544.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import edu.miu.cs.cs544.domain.Address;
import edu.miu.cs.cs544.domain.State;
import edu.miu.cs.cs544.dto.request.AddressRequest;
import edu.miu.cs.cs544.repository.StateRepository;

@Service
public class AddressService {
    @Autowired
    private StateRepository stateRepository;

    public Address createAddress(AddressRequest request) {
        String code = request.getStateCode();
        State state = stateRepository.findByCode(code).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "State (code='%s') Not Found".formatted(code)));

        Address address = AddressRequest.to(request);
        address.setState(state);
        return address;
    }
}
