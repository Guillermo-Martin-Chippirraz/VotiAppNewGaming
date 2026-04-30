import { LoginForm } from "../components/LoginForm"
import "./LoginPage.css";

export const LoginPage = () => {
    return (
        <div className="pantalla-movil-base login-container">
            <header className="login-header">
                <h2 className="login-subtitle">Bienvenido/a de nuevo</h2>
            </header>

            <main className="login-main">
                <h1 className="login-title">INICIAR SESIÓN</h1>
                <div className="login-form-wrapper">
                    <LoginForm />
                </div>
            </main>
        </div>
    );
};