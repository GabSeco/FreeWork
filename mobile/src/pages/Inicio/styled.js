import styled from "styled-components/native";

export const Titulo = styled.Text` 
    color: #0040ff;
    font-size: 35px;
    font-weight: 700;
    margin-top: 10px;
    text-align: center;
`;

export const Descricao = styled.Text` 
    color: #000080;
    font-weight: 300;
    max-width: 80%;
    margin-left: 10%;
    font-size: 16px;
    margin-top: 5px;
    margin-bottom: 10px;
    text-align: center;
`;

export const Button = styled.TouchableOpacity` 
    background: white;
    width: 100%;
    height: 63px;
    justify-content: center;
    border-radius: 6px;
    margin-top: 10px;
`;

export const ButtonText = styled.Text` 
    align-self: center;
    font-size: 16px;
    font-weight: 700;
    border-radius: 6px;
    color: #0000ff;
`;