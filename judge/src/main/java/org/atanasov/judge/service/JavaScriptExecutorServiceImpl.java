package org.atanasov.judge.service;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.logging.Logger;

public class JavaScriptExecutorServiceImpl implements JavaScriptExecutorService {

    private Logger logger = Logger.getLogger(JavaScriptExecutorServiceImpl.class.getName());

    private ScriptEngineManager factory = new ScriptEngineManager();
    private ScriptEngine engine = factory.getEngineByName("JavaScript");

    @Override
    public String execute(String code) {
        try {
            engine.eval(code);
            Invocable invocable = (Invocable) engine;
            String functionName = code.substring(code.indexOf("function ") + "function ".length(), code.indexOf("("));
            String funcResult = (String) invocable.invokeFunction(functionName);
            logger.info(funcResult);
            return funcResult;
        } catch (ScriptException | NoSuchMethodException e) {
            throw new IllegalArgumentException("Compile error");
        }
    }
}
