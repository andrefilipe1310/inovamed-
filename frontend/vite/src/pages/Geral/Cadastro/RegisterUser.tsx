import { useState } from "react"
import { Link, useSearchParams } from "react-router-dom"
import InputMask from 'react-input-mask';

export default function RegisterUser(){

    const [seachParams] = useSearchParams()
    const userType = seachParams.get('userType')

    const [showPassword, setShowPassword] = useState(false);

    const togglePasswordVisibility = () => {
    setShowPassword(!showPassword);
  };

    if (userType === "representative"){
        
      
    return(
        <div className="form-container">
          <div className="logo-container"><img src="../../../../public/logo_branca.svg" alt="logo" /></div>
          <h1 className="title-page" style={{marginTop:"0px"}}>CADASTRO</h1>
          <div className="form-header">
            <p>Representante Institucional</p>
            <button className="change-user"><Link to={"/cadastro"} className="ajust-link">Trocar</Link></button>
          </div>
          <div className="form" >
            <label htmlFor="nome">Nome completo <i>Obrigatório</i></label>
            <input
              type="text"
              id="name"
              name="name"
              required
            />

            <label htmlFor="email">Email corporativo <i>Obrigatório</i></label>
            <input
              type="email"
              id="email"
              name="email"
              required
            />

                <label htmlFor="instituicao">Instituição <i>Obrigatório</i></label>
                <input
                  type="text"
                  id="institution"
                  name="institution"
                  required
                />

              <div className="phone-number">
              <label htmlFor="contato">N° para contato <i>Obrigatório</i></label>
                <InputMask
                  mask="(99)99999-9999"
                  required
                  >
                  {(inputProps) => <input {...inputProps} type="tel" />}
                </InputMask>
              </div>
            

            <label htmlFor="cargo">Cargo/função <i>Obrigatório</i></label>
            <input
              type="text"
              id="function"
              name="function"
              required
            />

            <label htmlFor="experiencias">Experiências/qualificações <i>Obrigatório</i></label>
            <textarea
              id="exp"
              name="exp"
              rows={4}
            />
            <div className="ajust-section" >
              <div>
                <label htmlFor="senha">Criar senha <i>Obrigatório</i></label>
                <input
                  type={showPassword ? 'text' : 'password'}
                  id="password"
                  name="password"
                  required
                />
              </div>
              <button onClick={togglePasswordVisibility}>see</button>
            </div>

            <button type="submit" className="submit">FAZER CADASTRO</button>
          </div>
    </div>
    )
}
    else{
        return(
            <div>AINDA EM PRODUÇÃO!!</div>
        )
    }

}