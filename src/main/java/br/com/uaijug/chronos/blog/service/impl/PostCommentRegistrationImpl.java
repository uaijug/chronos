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
package br.com.uaijug.chronos.blog.service.impl;

import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import br.com.uaijug.chronos.blog.model.PostComment;
import br.com.uaijug.chronos.blog.service.PostCommentRegistration;
import br.com.uaijug.chronos.persistence.base.PersistenceBase;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class PostCommentRegistrationImpl extends PersistenceBase<PostComment, Long> implements PostCommentRegistration {

    @Inject
    private Logger log;
    
    @Inject
    private Event<PostComment> postCommentEventSrc;

    @Override
	public Boolean register(PostComment postComment) {
		Boolean saved = false;
		if (exists(postComment.getId())) {
			try {
				saved = false;
				throw new Exception("Register exits!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			postComment.setCreateTime(new Date());
			postComment.setUpdateTime(new Date());

			if (postComment.getId() != null) {
				log.info("Updating " + postComment.getContent());
				update(postComment);
			} else {
				log.info("Registering " + postComment.getContent());
				save(postComment);
			}
		}
		postCommentEventSrc.fire(postComment);
		return saved;
	}
}
