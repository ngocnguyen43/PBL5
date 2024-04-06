package service;

import jakarta.inject.Named;
import utils.exceptions.api.InvalidEndpointException;
@Named
public class InjectService implements IInjectService {
    @Override
    public void Test() throws InvalidEndpointException {
    throw  new InvalidEndpointException();
    }
}
