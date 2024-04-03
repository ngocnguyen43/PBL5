package org.example.ticketbox.Service;

import org.example.ticketbox.utils.exceptions.api.InvalidEndpointException;

public class InjectService implements IInjectService {
    @Override
    public void Test() throws InvalidEndpointException {
    throw  new InvalidEndpointException();
    }
}
