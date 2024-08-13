package calv.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import calv.entities.Aluno;
import calv.entities.Identity;
import calv.services.AlunoService;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController 
{
    @Autowired
    private AlunoService alunoService;

    @PostMapping
    public ResponseEntity<Object> alunoLogin(@RequestBody Aluno aluno) 
    {
        Optional<Aluno> alunoRes = this.alunoService.findByEmail(aluno.getEmail());
        if (alunoRes.isEmpty())
        {
            return ResponseEntity.status(404).build();
        }

        Identity identity = new Identity();
        System.out.println(identity.getToken());

        return new ResponseEntity<Object>(identity, HttpStatus.OK);
    }
}