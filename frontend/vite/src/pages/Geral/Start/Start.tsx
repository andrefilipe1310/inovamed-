import { Link } from "react-router-dom";
import './Start.css';

export default function Start() {
  return (
    <div className="container-all-start">
      <div className="header-container">
        <img
          src="logo_branca.svg"
          alt="logo"
          className="logo-start"
        />
        <Link to="/login" className="link-login">
          Login
        </Link>
      </div>

      <div className="front-page">
        <div className="text-circle-container">
          <div>
            <h1>TRANSPARÊNCIA EM 1º LUGAR</h1>
            <p>
              A Inovamed é reconhecida por sua transparência na divulgação de pesquisas científicas, proporcionando acesso aberto a dados e fontes, o que permite a verificação rigorosa dos resultados. O site adota práticas de revisão por pares criteriosas e públicas, garantindo uma ciência aberta e acessível.
            </p>
          </div>
        </div>
        <div className="doctor-image-container">
          <img
            src="medico.png"
            alt="Médico"
            className="doctor-image"
          />
        </div>
        <div className="login-button-container">
          <Link to="/login" className="login-button">Login</Link>
        </div>
      </div>

      <div className="rodape">
        <img
          src="logo_branca.svg"
          alt="logo"
          className="logo-start"
        />
        <p>Inovamed - Rope Health</p>
        <p>Todos os direitos reservados</p>
      </div>
    </div>
  );
}
