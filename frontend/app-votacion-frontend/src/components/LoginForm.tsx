import { useState } from "react";
import { useAuth } from "../hooks/useAuth";
import { InputTexto } from "./InputTexto";
import "./LoginForm.css";

export const LoginForm = () => {
    const { login, state } = useAuth();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleSubmit = async (e: React.SyntheticEvent) => {
        e.preventDefault();
        
        if (!username.trim() || !password.trim()) return;
        
        await login({ username, password });
    };

    return (
        <form className="login-form" onSubmit={handleSubmit}>
            <InputTexto
                placeholder="NOMBRE DE USUARIO"
                valor={username}
                onChange={setUsername}
                tipo="text"
            />

            <InputTexto
                placeholder="CONTRASEÑA"
                valor={password}
                onChange={setPassword}
                tipo="password"
            />

            <div className="login-btn-wrapper">
                <button 
                    className="btn-primary" 
                    type="submit" 
                    disabled={state.loading}
                >
                    {state.loading ? "ENTRANDO..." : "ENTRAR"}
                </button>
            </div>
        </form>
    );
};