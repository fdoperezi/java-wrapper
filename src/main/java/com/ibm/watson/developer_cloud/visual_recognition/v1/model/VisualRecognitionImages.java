/**
 * Copyright 2015 IBM Corp. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ibm.watson.developer_cloud.visual_recognition.v1.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class VisualRecognitionImages {

	@Expose
	private List<RecognizedImage> images = new ArrayList<RecognizedImage>();

	/**
	 * Gets the images.
	 * 
	 * @return The images
	 */
	public List<RecognizedImage> getImages() {
		return images;
	}

	/**
	 * Sets the images.
	 * 
	 * @param images
	 *            The images
	 */
	public void setImages(List<RecognizedImage> images) {
		this.images = images;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClass().getName() + " "
				+ new GsonBuilder().setPrettyPrinting().create().toJson(this);
	}

}
