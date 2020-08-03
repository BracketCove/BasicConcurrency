package com.wiseassblog.basicconcurrency.common;

import com.wiseassblog.basicconcurrency.R;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class ResultWrapper<R> {

    protected Object result;
    protected Exception exception;

    public ResultWrapper<R> build(Supplier<R> function) {
        try {
            return new ResultWrapper.Success(
                    function.get()
            );
        } catch (Exception e) {
            return new ResultWrapper.Failure(e);
        }

    }

    public static class Success extends ResultWrapper {
        private Success(Object result) {
            this.result = result;
            this.exception = null;
        }

        public Object getResult() {
            return result;
        }
    }

    public static class Failure extends ResultWrapper {
        private Failure(Exception exception) {
            this.result = null;
            this.exception = exception;
        }

        public Exception getException() {
            return exception;
        }
    }

}
