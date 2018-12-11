package bll;

import dal.entities.Pass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import dal.repositories.PassRepository;

import java.util.List;

@Service
public class PassBll {
    @Autowired
    PassRepository passRepository;

    public List<Pass> getAllPasses() {
        return passRepository.findAll();
    }

    public Pass getPassById(int id) {
        if (passRepository.findById(id).isPresent())
            return passRepository.findById(id).get();
        else
            return null;
    }

    public void addPass(Pass pass) {
        passRepository.save(pass);
    }

    public String updatePass(Pass pass) {
        Pass updatedPass;
        if (passRepository.findById(pass.getId()).isPresent()) {
            updatedPass = passRepository.findById(pass.getId()).get();
            updatedPass.setType(pass.getType());
            updatedPass.setPrice(pass.getPrice());
            passRepository.save(updatedPass);
            return "PASS UPDATE SUCCESSFUL";
        } else
            return "PASS UPDATE FAILED";
    }

    public void deletePass(int passId) {
        passRepository.deleteById(passId);
    }
}
