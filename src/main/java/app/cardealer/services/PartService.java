package app.cardealer.services;

import app.cardealer.models.binding.PartCreateBindingModel;
import app.cardealer.models.view.PartQuantityViewModel;
import app.cardealer.models.view.PartViewModel;

import java.util.List;

public interface PartService {

    void importPart(PartCreateBindingModel partBindingModel);

    List<PartViewModel> extractParts();

    PartViewModel extractPartById(Long id);

    void removePartById(Long id);

    PartQuantityViewModel extractPartQuantityById(Long id);

    void editPart(Long id, PartCreateBindingModel partBindingModel);
}
