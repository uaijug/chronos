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
package br.com.uaijug.chronos.blog.data.producer;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.uaijug.chronos.blog.data.repository.PostCategoryRepository;
import br.com.uaijug.chronos.blog.model.PostCategory;

// TODO: Auto-generated Javadoc
/**
 * The Class PostCategoryListProducer.
 * 
 * @author Rogerio Fontes - http://www.rogeriofontes.inf.br - rogerio.fontes at rogeriofontes dot inf dot br
 * 
 */
@RequestScoped
public class PostCategoryListProducer {

    /** The post category repository. */
    @Inject
    private PostCategoryRepository postCategoryRepository;

    /** The post categorys. */
    private List<PostCategory> postCategorys;

    // @Named provides access the return value via the EL variable name "posts" in the UI (e.g.
    // Facelets or JSP view)
    /**
     * Gets the post categorys.
     *
     * @return the post categorys
     */
    @Produces
    @Named
    public List<PostCategory> getPostCategorys() {
        return postCategorys;
    }

    /**
     * On post list changed.
     *
     * @param postCategory the post category
     */
    public void onPostListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final PostCategory postCategory) {
        retrieveAllPostsOrderedByDescription();
    }

    /**
     * Retrieve all posts ordered by description.
     */
    @PostConstruct
    public void retrieveAllPostsOrderedByDescription() {
        postCategorys = postCategoryRepository.findAllOrderedByName();
    }

}
