/*
 * LensKit, an open source recommender systems toolkit.
 * Copyright 2010-2014 LensKit Contributors.  See CONTRIBUTORS.md.
 * Work on LensKit has been funded by the National Science Foundation under
 * grants IIS 05-34939, 08-08692, 08-12148, and 10-17697.
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc., 51
 * Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package org.lenskit.eval.traintest.recommend;

import org.lenskit.api.ResultList;
import org.lenskit.eval.traintest.TestUser;
import org.lenskit.eval.traintest.metrics.Metric;
import org.lenskit.eval.traintest.metrics.MetricResult;
import org.lenskit.eval.traintest.metrics.TypedMetricResult;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Base class for metrics that measure Top-N recommendations for users.
 *
 * @param <X> The context type.
 */
public abstract class TopNMetric<X> extends Metric<X> {
    /**
     * Construct a new result metric.
     * @param labels Column labels.
     * @param aggLabels Aggregate column labels.
     */
    protected TopNMetric(List<String> labels, List<String> aggLabels) {
        super(labels, aggLabels);
    }

    /**
     * Construct a new result metric.
     * @param resType The result type for measuring results, or `null` for no measurement.
     * @param aggType The result type for aggregate measurements, or `null` for no measurement.
     */
    protected TopNMetric(Class<? extends TypedMetricResult> resType,
                         Class<? extends TypedMetricResult> aggType) {
        super(TypedMetricResult.getColumns(resType),
              TypedMetricResult.getColumns(aggType));
    }

    /**
     * Construct a new result metric.
     * @param resType The result type for measuring results, or `null` for no measurement.
     * @param aggType The result type for aggregate measurements, or `null` for no measurement.
     * @param suffix Suffix to apply to names.
     */
    protected TopNMetric(Class<? extends TypedMetricResult> resType,
                         Class<? extends TypedMetricResult> aggType,
                         String suffix) {
        super(TypedMetricResult.getColumns(resType, suffix),
              TypedMetricResult.getColumns(aggType, suffix));
    }

    /**
     * Measure a single result.  The result may come from either prediction or recommendation.
     * @param user The user's test data.
     * @param recommendations The user's recommendations.
     * @return A list of fields to add to the result's output.
     */
    @Nonnull
    public abstract MetricResult measureUser(TestUser user,
                                             ResultList recommendations,
                                             X context);
}
