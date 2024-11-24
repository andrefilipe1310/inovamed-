import { useState, useEffect } from "react";
import Navbar from "../../components/Navbar";
import { NotificationRequestDTO } from "../../types/NotificationType";
import api from "../../config/axiosConfig";

export default function RepNotificacoes() {
  const [pesqInfo, setPesqInfo] = useState<any[]>([]);
  const [cod, setCod] = useState<number>(-1);
  const [files, setFiles] = useState<File[]>([]);
  const [notificationError, setNotificationError] = useState<string>("");
  const [notification, setNotification] = useState<NotificationRequestDTO>({
    title: "",
    message: "",
    doctorsId: [],
    patientsId: [],
  });

  useEffect(() => {
    const fetchResearches = async () => {
      try {
        const response = await api.get("/research/feature");
        setPesqInfo(response.data);
      } catch (error) {
        console.error("Erro ao buscar pesquisas:", error);
      }
    };
    fetchResearches();
  }, []);

  const handleFiles = (e: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFiles = e.target.files;
    if (selectedFiles) {
      const fileArray = Array.from(selectedFiles);
      setFiles((prevFiles) => [...prevFiles, ...fileArray]);
    }
  };

  const handleInputChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>
  ) => {
    const { name, value } = e.target;
    setNotification((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handlefindPatientsInResearch = async (researchCode: number) => {
    try {
      const response = await api.get(`/${researchCode}/participants`);
      setNotification((prev) => ({
        ...prev,
        doctorsId: response.data.doctorsId,
        patientsId: response.data.patientsId,
      }));
    } catch (error) {
      console.error("Erro ao buscar participantes:", error);
      setNotificationError("Erro ao carregar participantes da pesquisa.");
    }
  };

  const handleCreateNotification = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    const formData = new FormData();

    formData.append("title", notification.title);
    formData.append("message", notification.message);
    formData.append("doctorsId", JSON.stringify(notification.doctorsId));
    formData.append("patientsId", JSON.stringify(notification.patientsId));
    files.forEach((file) => formData.append("files", file));

    try {
      const response = await api.post("/notification", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      console.log("Notificação enviada com sucesso!", response.data);
      setNotificationError("");
    } catch (error) {
      console.error("Erro ao enviar notificação:", error);
      setNotificationError("Erro ao enviar notificação. Tente novamente.");
    }
  };

  return (
    <>
      <Navbar />
      <div className="container-page">
        <h1 className="title-page">NOTIFICAÇÕES</h1>
        <form onSubmit={handleCreateNotification} className="card-border">
          <div className="container-notfic-all">
            <label htmlFor="title">TÍTULO DA NOTIFICAÇÃO</label>
            <input
              type="text"
              name="title"
              value={notification.title}
              onChange={handleInputChange}
            />

            <label htmlFor="message">CONTEÚDO</label>
            <textarea
              name="message"
              value={notification.message}
              onChange={handleInputChange}
              id="content"
            ></textarea>

            <label htmlFor="files" style={{ alignSelf: "center" }}>
              ANEXOS
            </label>
            <div className="files-arrange" style={{ alignSelf: "center" }}>
              <input type="file" name="files" multiple onChange={handleFiles} />
            </div>
            <div style={{ display: "flex", flexWrap: "wrap" }}>
              {files.map((f, index) => (
                <div className="files-custom" key={index}>
                  {f.name}
                </div>
              ))}
            </div>

            <div className="container-select">
              <select
                name="selectTypeUser"
                onChange={(e) => {
                  const researchCode = Number(e.target.value);
                  setCod(researchCode);
                  handlefindPatientsInResearch(researchCode);
                }}
              >
                <option value="-1">Selecione a pesquisa</option>
                {pesqInfo.map((info) => (
                  <option value={info.code} key={info.code}>
                    {info.code}
                  </option>
                ))}
              </select>

              <select
                name="selectWho"
                onChange={(e) =>
                  setNotification((prev) => ({
                    ...prev,
                    patientsId: [Number(e.target.value)],
                  }))
                }
              >
                <option value="-1">Selecione o paciente</option>
                {cod >= 0 &&
                  pesqInfo[cod]?.patients.map((info: any) => (
                    <option value={info.id} key={info.id}>
                      {info.name}
                    </option>
                  ))}
              </select>
            </div>
          </div>
          <button type="submit" className="button-send-notification">
            ENVIAR NOTIFICAÇÃO
          </button>
        </form>
        {notificationError && <p className="error">{notificationError}</p>}
      </div>
    </>
  );
}
