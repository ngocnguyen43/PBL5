package main.service;

import main.utils.exceptions.api.InvalidEndpointException;

public class InjectService implements IInjectService {
    @Override
    public void Test() throws InvalidEndpointException {
    throw  new InvalidEndpointException();
    }
}
