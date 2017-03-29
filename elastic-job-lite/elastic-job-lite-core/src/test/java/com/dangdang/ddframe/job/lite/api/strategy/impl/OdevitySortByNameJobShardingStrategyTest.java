/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
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
 * </p>
 */

package com.dangdang.ddframe.job.lite.api.strategy.impl;

import com.dangdang.ddframe.job.lite.api.strategy.JobShardingResult;
import com.dangdang.ddframe.job.lite.api.strategy.JobShardingMetadata;
import com.dangdang.ddframe.job.lite.api.strategy.JobInstance;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public final class OdevitySortByNameJobShardingStrategyTest {
    
    private OdevitySortByNameJobShardingStrategy odevitySortByNameJobShardingStrategy = new OdevitySortByNameJobShardingStrategy();
    
    @Test
    public void assertShardingByAsc() {
        Collection<JobShardingResult> expected = new LinkedList<>();
        expected.add(new JobShardingResult(new JobInstance("host0@-@0"), Collections.singletonList(0)));
        expected.add(new JobShardingResult(new JobInstance("host1@-@0"), Collections.singletonList(1)));
        expected.add(new JobShardingResult(new JobInstance("host2@-@0"), Collections.<Integer>emptyList()));
        assertThat(odevitySortByNameJobShardingStrategy.sharding(Arrays.asList(
                new JobInstance("host0@-@0"), new JobInstance("host1@-@0"), new JobInstance("host2@-@0")), 
                new JobShardingMetadata("1", 2)), is(expected));
    }
    
    @Test
    public void assertShardingByDesc() {
        Collection<JobShardingResult> expected = new LinkedList<>();
        expected.add(new JobShardingResult(new JobInstance("host2@-@0"), Collections.singletonList(0)));
        expected.add(new JobShardingResult(new JobInstance("host1@-@0"), Collections.singletonList(1)));
        expected.add(new JobShardingResult(new JobInstance("host0@-@0"), Collections.<Integer>emptyList()));
        assertThat(odevitySortByNameJobShardingStrategy.sharding(
                Arrays.asList(new JobInstance("host0@-@0"), new JobInstance("host1@-@0"), new JobInstance("host2@-@0")),
                new JobShardingMetadata("0", 2)), is(expected));
    }
}
