package test.polynomial.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.polynomial.interfaces.IPolynomialService;
import test.polynomial.pojo.PolynomialResponse;
import test.polynomial.pojo.PolynomialRequest;

/**
 * Controller for polynomial expressions parsing and solving
 */
@RestController
@RequestMapping("/")
public class PolynomialController {

    @Autowired
    private IPolynomialService service;

    /**
     * POST method for parsing expressions/solving functions
     * @param request {@link PolynomialRequest} POST body
     * @return ResponseEntity response
     */
    @PostMapping("polynomial")
    public ResponseEntity<PolynomialResponse> parse(@RequestBody PolynomialRequest request) {
        PolynomialResponse response = new PolynomialResponse();
        try {
            if (request.getArgument() == null) {
                return ResponseEntity.ok().body(new PolynomialResponse().setExpression(service.parse(request.getExpression())));
            } else {
                return ResponseEntity.ok().body(response.setResult(service.solve(request.getExpression(), request.getArgument())));
            }
        }
        catch (IllegalArgumentException | HttpMessageNotReadableException e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(400).body(response.setMessage("Error: " + e.getMessage()));
        }
    }
}
