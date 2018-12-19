package bll.crud;

import bll.dtos.PassDTO;
import bll.dtos.converters.PassConverter;
import dal.entities.Pass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dal.repositories.PassRepository;

import java.util.List;

@Service
public class PassBll {
    @Autowired
    PassRepository passRepository;
    @Autowired
    private PassConverter converter;// = new PassConverter();

    public List<PassDTO> getAllPasses() {
        return converter.entityListToDtoList(passRepository.findAll());
    }

    public PassDTO getPassById(int id) {
        if (passRepository.findById(id).isPresent())
            return converter.entityToDto(passRepository.findById(id).get());
        else
            return null;
    }

    public String addPass(PassDTO pass) {
        passRepository.save(converter.dtoToEntity(pass));
        return "PASS ADD SUCCESSFUL";
    }

    public String updatePass(PassDTO pass) {
        Pass updatedPass;
        String reason = "Pass";
        if (passRepository.findById(pass.getId()).isPresent()) {
            updatedPass = passRepository.findById(pass.getId()).get();
            updatedPass.setType(pass.getType());
            updatedPass.setPrice(pass.getPrice());
            passRepository.save(updatedPass);
            return "PASS UPDATE SUCCESSFUL";
        }
        return "PASS UPDATE FAILED: " + reason + " with this ID doesn't exist";
    }

    public String deletePass(int passId) {
        String reason = "Pass";
        if (passRepository.findById(passId).isPresent()) {
            passRepository.deleteById(passId);
            return "PASS DELETE SUCCESSFUL";
        }
        return "PASS DELETE FAILED: " + reason + " with this ID doesn't exist";
    }
}
