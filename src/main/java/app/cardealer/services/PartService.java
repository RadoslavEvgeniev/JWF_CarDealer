package app.cardealer.services;

import app.cardealer.models.binding.PartCreateBindingModel;

public interface PartService {

    void importPart(PartCreateBindingModel partBindingModel);
}
