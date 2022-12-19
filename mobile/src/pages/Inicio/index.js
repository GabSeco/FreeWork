import { useNavigation } from "@react-navigation/native";
import { SafeAreaView, Image, ScrollView } from "react-native";
import { Titulo, Descricao, Button, ButtonText } from "./styled";

export default function Inicio(){
    const navigation = useNavigation();

    return(
        <SafeAreaView
        style={{ flexDirection: 'column', alignItems: 'center', flex: 1, backgroundColor: 'white' }}
        >
            <ScrollView
            showsVerticalScrollIndicator={false}
            style={{ flexDirection: 'column', flex: 1, paddingVertical: 30, paddingHorizontal: 15 }}
            >
                <Image style={{ alignSelf: 'center', marginTop: 35 }} source={require('../../../assets/miniLogo.png')}/>

                <Image style={{ marginTop: 7 }} source={require('../../../assets/ImagemFundo.png')}/>

                <Titulo>
                    FREEWORK
                </Titulo>

                <Descricao>
                    O app para conectar você com o serviço que deseja.
                </Descricao>

                <Button
                onPress={() => navigation.navigate('Cadastro')}
                >
                    <ButtonText>
                        Cadastre-se
                    </ButtonText>
                </Button>

                <Button
                onPress={() => navigation.navigate('Login')}
                style={{ backgroundColor: 'white', borderWidth: 1, borderColor: 'black', marginBottom: 55 }}>
                    <ButtonText style={{ color: 'blue' }}>
                        Login
                    </ButtonText>
                </Button>
            </ScrollView>
        </SafeAreaView>
    );
}