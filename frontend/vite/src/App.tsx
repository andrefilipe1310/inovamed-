import { BrowserRouter, Routes, Route } from "react-router-dom"

import Login from "./pages/Geral/Login/Login"
import Start from "./pages/Geral/Start/Start"
import PacListPesquisas from "./pages/Paciente/listPesquisas"
import PacVerNotificacoes from "./pages/Paciente/notificacoes"
import PacPreferencias from "./pages/Paciente/preferencias"
import MedListPacientesPesquisas from "./pages/Medico/listaPacientesPesquisas_med"
import Medlistapesqinfo from "./pages/Medico/listpesqinfo_med"
import MedCriarNotificacao from "./pages/Medico/criarNotificacao_med"
import MedNotificacao from "./pages/Medico/notificacao_med"
import MedParticipantes from "./pages/Medico/participantes_med"
import MedParticipantesConfig from "./pages/Medico/configuracaoPartarticipante_med"
import RepListaPesquisas from "./pages/Representante/listPesquisas"
import RepAlterarPesquisa from "./pages/Representante/alterarPesquisa"
import RepNovaPesquisa from "./pages/Representante/novaPesquisa"
import RepNotificacoes from "./pages/Representante/notificacoes"
import RepPesquisaInfo from './pages/Representante/PesquisaInfo'
import ListaPac from "./pages/Representante/listpac"
import RedirectUserType from "./pages/Geral/Cadastro/pageRedirectUserType"
import RegisterRepresentative from "./pages/Geral/Cadastro/RegisterRepresentative"
import ResgisterPatient from "./pages/Geral/Cadastro/RegisterPatient"
import RegisterDoctor from "./pages/Geral/Cadastro/RegisterDoctor"
import InfoPesqPatient from "./pages/Paciente/infoPesqPatient"



export default function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Start/>}></Route>
        <Route path="/login" element={<Login/>}></Route>
        <Route path="/cadastro" element={<RedirectUserType/>}></Route>
        <Route path="/cadastro/representante" element={<RegisterRepresentative/>}></Route>
        <Route path="/cadastro/medico" element={<RegisterDoctor/>}></Route>
        <Route path="/cadastro/paciente" element={<ResgisterPatient/>}></Route>
        <Route path="/paciente/notificacoes" element={<PacVerNotificacoes/>}></Route>
        <Route path="/paciente/inforPesq" element={<InfoPesqPatient/>}></Route>
        <Route path="/paciente/listapesquisas" element={<PacListPesquisas/>}></Route>
        <Route path="/paciente/preferencias" element={<PacPreferencias/>}></Route>
        <Route path="/medico/listapesquisas" element={<MedListPacientesPesquisas/>}></Route>
        <Route path="/medico/listapesquisainfo" element={<Medlistapesqinfo/>}></Route>
        <Route path="/medico/participantes" element={<MedParticipantes/>}></Route>
        <Route path="/medico/participantesconfiguracao" element={<MedParticipantesConfig/>}></Route>
        <Route path="/medico/criarnotificacao" element={<MedCriarNotificacao/>}></Route>
        <Route path="/medico/notificacoes" element={<MedNotificacao/>}></Route>
        <Route path="/representante/listapesquisas" element={<RepListaPesquisas/>}></Route>
        <Route path="/representante/infoPesquisas" element={<RepPesquisaInfo/>}></Route>
        <Route path="/representante/alterarpesquisa" element={<RepAlterarPesquisa/>}></Route>
        <Route path="/representante/infopaciente" element={<ListaPac/>}></Route>
        <Route path="/representante/novapesquisa" element={<RepNovaPesquisa/>}></Route>
        <Route path="/representante/notificacoes" element={<RepNotificacoes/>}></Route>
      </Routes>
    </BrowserRouter>
  )
}