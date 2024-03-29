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
package br.com.uaijug.chronos.event.resources;

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

import br.com.uaijug.chronos.event.data.repository.EventScheduleRepository;
import br.com.uaijug.chronos.event.model.EventSchedule;
import br.com.uaijug.chronos.event.service.EventScheduleRegistration;

// TODO: Auto-generated Javadoc
/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the
 * eventSchedules table.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogerifontes dot inf dot br
 * 
 */
@Path("/eventSchedules")
@RequestScoped
public class EventScheduleResource {

	/** The log. */
	@Inject
	private Logger log;

	/** The validator. */
	@Inject
	private Validator validator;

	/** The repository. */
	@Inject
	private EventScheduleRepository repository;

	/** The registration. */
	@Inject
	EventScheduleRegistration registration;

	/**
	 * List all event schedules.
	 *
	 * @return the list
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<EventSchedule> listAllEventSchedules() {
		return repository.findAllOrderedByName();
	}

	/**
	 * Lookup event schedule by id.
	 *
	 * @param id the id
	 * @return the event schedule
	 */
	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public EventSchedule lookupEventScheduleById(@PathParam("id") long id) {
		EventSchedule eventSchedule = repository.findById(id);
		if (eventSchedule == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return eventSchedule;
	}
	
/*	@GET
	@Path("/s/{slug:[a-z][a-z]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public EventSchedule lookupEventScheduleBySlug(@PathParam("slug") String slug) {
		EventSchedule eventSchedule = repository.findBySlug(slug);
		if (eventSchedule == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return eventSchedule;
	}*/
	
	/**
 * Creates a new eventSchedule from the values provided. Performs validation, and
 * will return a JAX-RS response with either 200 ok, or with a map of
 * fields, and related errors.
 *
 * @param eventSchedule the event schedule
 * @return the response
 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createEventSchedule(EventSchedule eventSchedule) {

		Response.ResponseBuilder builder = null;

		try {
			// Validates eventSchedule using bean validation
			validateEventSchedule(eventSchedule);

			registration.register(eventSchedule);

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
	 * Validates the given EventSchedule variable and throws validation exceptions
	 * based on the type of error. If the error is standard bean validation
	 * errors then it will throw a ConstraintValidationException with the set of
	 * the constraints violated.
	 * </p>
	 * <p>
	 * If the error is caused because an existing eventSchedule with the same email is
	 * registered it throws a regular validation exception so that it can be
	 * interpreted separately.
	 * </p>
	 * 
	 * @param eventSchedule
	 *            EventSchedule to be validated
	 * @throws ConstraintViolationException
	 *             If Bean Validation errors exist
	 * @throws ValidationException
	 *             If eventSchedule with the same email already exists
	 */
	private void validateEventSchedule(EventSchedule eventSchedule)
			throws ConstraintViolationException, ValidationException {
		// Create a bean validator and check for issues.
		Set<ConstraintViolation<EventSchedule>> violations = validator
				.validate(eventSchedule);

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
