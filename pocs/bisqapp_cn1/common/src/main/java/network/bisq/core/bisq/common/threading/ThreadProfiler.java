/*
 * This file is part of Bisq.
 *
 * Bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * Bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package network.bisq.core.bisq.common.threading;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.Optional;

public class ThreadProfiler {
    public static final ThreadProfiler INSTANCE = new ThreadProfiler();
    private final Optional<ThreadMXBean> optionalThreadMXBean;
    private final Optional<com.sun.management.ThreadMXBean> optionalSunThreadMXBean;

    private ThreadProfiler() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        if (threadMXBean.isThreadCpuTimeSupported()) {
            if (!threadMXBean.isThreadCpuTimeEnabled()) {
                threadMXBean.setThreadCpuTimeEnabled(true);
            }
            optionalThreadMXBean = Optional.of(threadMXBean);
        } else {
            optionalThreadMXBean = Optional.empty();
        }

        if (threadMXBean instanceof com.sun.management.ThreadMXBean) {
            com.sun.management.ThreadMXBean sunThreadMXBean = (com.sun.management.ThreadMXBean) threadMXBean;
            if (sunThreadMXBean.isThreadAllocatedMemorySupported()) {
                if (!sunThreadMXBean.isThreadAllocatedMemoryEnabled()) {
                    sunThreadMXBean.setThreadAllocatedMemoryEnabled(true);
                }
            }
            optionalSunThreadMXBean = Optional.of(sunThreadMXBean);
        } else {
            optionalSunThreadMXBean = Optional.empty();
        }
    }

    public Optional<Long> getThreadTime(long threadId) {
        return optionalThreadMXBean.map(mxBean -> mxBean.getThreadCpuTime(threadId));
    }

    public Optional<Long> getThreadMemory(long threadId) {
        return optionalSunThreadMXBean.map(mxBean -> mxBean.getThreadAllocatedBytes(threadId));
    }
}
