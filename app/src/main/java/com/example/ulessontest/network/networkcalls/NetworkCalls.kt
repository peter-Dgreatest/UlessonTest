/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.example.msalehstoreapp.network.networkcalls

import com.example.ulessontest.domains.NetworkLessonContainer
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

// Since we only have one service, this can all go in one file.
// If you add more services, split this to multiple files and make sure to share the retrofit
// object between services.

interface LessonService {
    @GET("lessons")
    fun getLiveLessons():
            Deferred<NetworkLessonContainer>


    @GET("lessons/me")
    fun getMyLessons():
            Deferred<NetworkLessonContainer>



}
