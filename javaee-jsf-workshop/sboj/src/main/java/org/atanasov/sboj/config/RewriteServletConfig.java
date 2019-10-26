package org.atanasov.sboj.config;

import org.ocpsoft.logging.Logger;
import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
import org.ocpsoft.rewrite.config.Configuration;
import org.ocpsoft.rewrite.config.ConfigurationBuilder;
import org.ocpsoft.rewrite.config.Direction;
import org.ocpsoft.rewrite.config.Log;
import org.ocpsoft.rewrite.context.EvaluationContext;
import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
import org.ocpsoft.rewrite.servlet.config.HttpOperation;
import org.ocpsoft.rewrite.servlet.config.Path;
import org.ocpsoft.rewrite.servlet.config.Redirect;
import org.ocpsoft.rewrite.servlet.config.rule.Join;
import org.ocpsoft.rewrite.servlet.http.event.HttpServletRewrite;

import javax.servlet.ServletContext;

import static org.atanasov.sboj.constants.Constants.*;

@RewriteConfiguration
public class RewriteServletConfig extends HttpConfigurationProvider {

    @Override
    public int priority() {
        return 10;
    }

    @Override
    public Configuration getConfiguration(final ServletContext context) {
        return ConfigurationBuilder.begin()
                .addRule()
                .when(Direction.isInbound().and(Path.matches("/{path}")))
                .perform(Log.message(Logger.Level.INFO, "Client requested path: {path}"))
                .where("path").matches(".*")

                .addRule()
                .when(Direction.isInbound().and(Path.matches(LOGOUT_URL)))
                .perform(new HttpOperation() {
                    @Override
                    public void performHttp(HttpServletRewrite event, EvaluationContext context) {
                        event.getRequest().getSession().invalidate();
                    }
                }.and(Redirect.temporary(context.getContextPath() + ROOT_URL)))

                .addRule()
                .when(Direction.isInbound().and(Path.matches(INDEX_URL)))
                .perform(Redirect.temporary(context.getContextPath() + ROOT_URL))

                .addRule(Join.path(ROOT_URL).to("/faces/views/index.xhtml"))
                .addRule(Join.path(HOME_URL).to("/faces/views/home.xhtml"))
                .addRule(Join.path(LOGIN_URL).to("/faces/views/login.xhtml"))
                .addRule(Join.path(REGISTER_URL).to("/faces/views/register.xhtml"));
    }
}
