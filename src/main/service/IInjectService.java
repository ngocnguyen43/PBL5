package main.service;

import main.utils.exceptions.api.InvalidEndpointException;

public interface IInjectService {
    void Test() throws InvalidEndpointException;
}
