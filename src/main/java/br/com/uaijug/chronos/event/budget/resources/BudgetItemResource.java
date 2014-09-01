/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.uaijug.chronos.event.budget.resources;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.uaijug.chronos.event.budget.data.repository.BudgetItemRepository;
import br.com.uaijug.chronos.event.budget.model.BudgetItem;
import br.com.uaijug.chronos.event.budget.service.BudgetItemRegistration;

// TODO: Auto-generated Javadoc
/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the
 * budgetItems table.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@Path("/budgetItems")
@RequestScoped
public class BudgetItemResource {

	/** The log. */
	@Inject
	private Logger log;

	/** The validator. */
	@Inject
	private Validator validator;

	/** The repository. */
	@Inject
	private BudgetItemRepository repository;

	/** The registration. */
	@Inject
	BudgetItemRegistration registration;

	/**
	 * List all budget items.
	 *
	 * @return the list
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<BudgetItem> listAllBudgetItems() {
		return repository.findAllOrderedByName();
	}

	/**
	 * Lookup budget item by id.
	 *
	 * @param id the id
	 * @return the budget item
	 */
	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public BudgetItem lookupBudgetItemById(@PathParam("id") long id) {
		BudgetItem budgetItem = repository.findById(id);
		if (budgetItem == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return budgetItem;
	}
	
/*	@GET
	@Path("/s/{slug:[a-z][a-z]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public BudgetItem lookupBudgetItemBySlug(@PathParam("slug") String slug) {
		BudgetItem budgetItem = repository.findBySlug(slug);
		if (budgetItem == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return budgetItem;
	}*/
	
	/**
 * Creates a new budgetItem from the values provided. Performs validation, and
 * will return a JAX-RS response with either 200 ok, or with a map of
 * fields, and related errors.
 *
 * @param budgetItem the budget item
 * @return the response
 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createBudgetItem(BudgetItem budgetItem) {

		Response.ResponseBuilder builder = null;

		try {
			// Validates budgetItem using bean validation
			validateBudgetItem(budgetItem);

			registration.register(budgetItem);

			// Create an "ok" response
			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			// Handle bean validation issues
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(
					responseObj);
		}

		return builder.build();
	}

	/**
	 * <p>
	 * Validates the given BudgetItem variable and throws validation exceptions
	 * based on the type of error. If the error is standard bean validation
	 * errors then it will throw a ConstraintValidationException with the set of
	 * the constraints violated.
	 * </p>
	 * <p>
	 * If the error is caused because an existing budgetItem with the same email is
	 * registered it throws a regular validation exception so that it can be
	 * interpreted separately.
	 * </p>
	 * 
	 * @param budgetItem
	 *            BudgetItem to be validated
	 * @throws ConstraintViolationException
	 *             If Bean Validation errors exist
	 * @throws ValidationException
	 *             If budgetItem with the same email already exists
	 */
	private void validateBudgetItem(BudgetItem budgetItem)
			throws ConstraintViolationException, ValidationException {
		// Create a bean validator and check for issues.
		Set<ConstraintViolation<BudgetItem>> violations = validator
				.validate(budgetItem);

		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(
					new HashSet<ConstraintViolation<?>>(violations));
		}

	}

	/**
	 * Creates a JAX-RS "Bad Request" response including a map of all violation
	 * fields, and their message. This can then be used by clients to show
	 * violations.
	 * 
	 * @param violations
	 *            A set of violations that needs to be reported
	 * @return JAX-RS response containing all violations
	 */
	private Response.ResponseBuilder createViolationResponse(
			Set<ConstraintViolation<?>> violations) {
		log.fine("Validation completed. violations found: " + violations.size());

		Map<String, String> responseObj = new HashMap<String, String>();

		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(),
					violation.getMessage());
		}

		return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	}
}
