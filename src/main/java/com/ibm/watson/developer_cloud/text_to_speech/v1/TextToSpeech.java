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
package com.ibm.watson.developer_cloud.text_to_speech.v1;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;

import com.google.gson.Gson;
import com.ibm.watson.developer_cloud.service.Request;
import com.ibm.watson.developer_cloud.service.WatsonService;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.VoiceSet;
import com.ibm.watson.developer_cloud.util.MediaType;
import com.ibm.watson.developer_cloud.util.ResponseUtil;

/**
 * The Text to Speech service uses IBM's speech synthesis capabilities to
 * convert English or Spanish text to an audio signal. The audio is streamed
 * back to the client with minimal delay.
 * 
 * @version v1
 * @author German Attanasio Ruiz <germanatt@us.ibm.com>
 * @see <a
 *      href="http://www.ibm.com/smarterplanet/us/en/ibmwatson/developercloud/text-to-speech.html">
 *      Text to Speech</a>
 */
public class TextToSpeech extends WatsonService {
	private static String URL = "https://stream.watsonplatform.net/text-to-speech-beta/api";

	/**
	 * Instantiates a new text to speech.
	 */
	public TextToSpeech() {
		setEndPoint(URL);
	}

	/**
	 * Synthesize.
	 * 
	 * @param text
	 *            the text
	 * @param voice
	 *            the voice
	 * @param format
	 *            the output format
	 * @return the input stream
	 */
	public InputStream synthesize(String text, Voice voice, String format) {
		if (text == null)
			throw new IllegalArgumentException("text can not be null");
		if (voice == null)
			throw new IllegalArgumentException("voice can not be null");

		Request request = Request.Get("/v1/synthesize");
		request.withQuery("text", text);
		request.withQuery("voice", voice.getName());

		if (format != null && !format.startsWith("audio/"))
			throw new IllegalArgumentException("format needs to be an audio mime type, for example: audio/wav or audio/ogg; codecs=opus");

		request.withQuery("accept", format != null ? format: MediaType.AUDIO_WAV);

		try {
			HttpResponse response = execute(request.build());
			InputStream is = ResponseUtil.getInputStream(response);
			return is;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Synthesize.
	 * 
	 * @param text
	 *            the text
	 * @param voice
	 *            the voice
	 * @return the input stream
	 */
	public InputStream synthesize(String text, Voice voice) {
		return synthesize(text, voice, MediaType.AUDIO_WAV);
	}


	/**
	 * Synthesize.
	 * 
	 * @param text
	 *            the text
	 * @param format
	 *            the format, it needs to be an audio mime type, for example: audio/wav or audio/ogg; codecs=opus
	 * @return the input stream
	 */
	public InputStream synthesize(String text, String format) {
		return synthesize(text, Voice.EN_MICHAEL, format);
	}

	/**
	 * Gets the voices.
	 * 
	 * @return the voices
	 */
	public VoiceSet getVoices() {
		HttpRequestBase request = Request.Get("/v1/voices").build();
		try {
			HttpResponse response = execute(request);
			String voiceSetJson = ResponseUtil.getString(response);
			VoiceSet voiceSet = new Gson().fromJson(voiceSetJson, VoiceSet.class);
			return voiceSet;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
