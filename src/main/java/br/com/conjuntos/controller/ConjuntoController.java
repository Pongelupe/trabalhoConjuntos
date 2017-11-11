package br.com.conjuntos.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.conjuntos.model.ConjuntoHandler;

@Controller
public class ConjuntoController {

	@RequestMapping(value = "conjuntos", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> cadastrarConjuntos(@RequestBody String json) {
		try {
			ConjuntoHandler handler = new ConjuntoHandler(new JSONObject(json));
			handler.execute();

			return ResponseEntity.ok(handler.toJson().toString());

		} catch (JSONException e) {
			return ResponseEntity.badRequest().body(e.toString());
		}
	}

	@RequestMapping(value = "/batata")
	public String batata() {
		return "batata";
	}

}
