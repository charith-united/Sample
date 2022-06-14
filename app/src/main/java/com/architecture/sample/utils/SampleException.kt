package com.architecture.sample.utils

import java.util.concurrent.CancellationException

class SampleException(val error: AppConstants.Error): CancellationException(error.message)