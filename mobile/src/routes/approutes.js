import { createBottomTabNavigator } from "@react-navigation/bottom-tabs"
import Home from "../pages/Home";
import { MaterialCommunityIcons } from '@expo/vector-icons';
import { Ionicons } from '@expo/vector-icons';
import Pesquisa from "../pages/Pesquisa";
import Servicos from "../pages/Servicos";
import Perfil from "../pages/Perfil";
import { createNativeStackNavigator } from "@react-navigation/native-stack";
import Servico from "../pages/Servico";
import Contratacao from "../pages/Contratacao";
import Pedido from "../pages/Pedido";
import Contratado from "../pages/Contratado";
import Usuario from "../pages/Usuario";

function PerfilStack(){
    const Stack = createNativeStackNavigator();

    return(
        <Stack.Navigator
        screenOptions={{
            headerShown: false
        }}
        >
            <Stack.Screen name="Buscas" component={Pesquisa} />

            <Stack.Screen name="Perfil" component={Perfil} />

            <Stack.Screen name="Servico" component={Servico} />

            <Stack.Screen name="Contratacao" component={Contratacao} />

            <Stack.Screen name="Contratado" component={Contratado} />
        </Stack.Navigator>
    )
}

function PedidoStack(){
    const Stack = createNativeStackNavigator();

    return(
        <Stack.Navigator
        screenOptions={{
            headerShown: false
        }}
        >
            <Stack.Screen name="Servicoss" component={Servicos} />

            <Stack.Screen name="Pedido" component={Pedido} />
        </Stack.Navigator>
    )
}

export default function AppRoutes(props){
    const Tab = createBottomTabNavigator();

    return(
        <Tab.Navigator
        backBehavior="history"
        screenOptions={{
            tabBarHideOnKeyboard: true,
            headerShown: false,
            tabBarActiveTintColor: '#000fff',
            keyboardHidesTabBar: true
        }}
        >
            <Tab.Screen name="Home" component={Home}
            options={{
                tabBarIcon: ({ focused }) => (
                    <MaterialCommunityIcons name={focused ? "home" : "home-outline"} size={24} color={focused ? "#000fff" : "#B2ACAB"} />
                ),
            }}
            />

            <Tab.Screen name="Pesquisa" component={PerfilStack}
            listeners={({ navigation }) => ({
                blur: () => navigation.setParams({ screen: undefined })
            })}
            options={{
                unmountOnBlur: true,
                tabBarIcon: ({ focused }) => (
                    <Ionicons name="search-sharp" size={24} color={focused ? "#000fff" : "#B2ACAB"} />
                ),
            }}
            />

            <Tab.Screen name="Servicos" component={PedidoStack}
            listeners={({ navigation }) => ({
                blur: () => navigation.setParams({ screen: undefined })
            })}
            options={{
                unmountOnBlur: true,
                tabBarLabel: 'Serviços',
                tabBarIcon: ({ focused }) => (
                    <Ionicons name="md-list-sharp" size={24} color={focused ? "#000fff" : "#B2ACAB"} />
                ),
            }}
            />

            <Tab.Screen name="Usuario" component={Usuario}
            options={{
                tabBarItemStyle: { display: 'none' },
            }}
            />

            
        </Tab.Navigator>
    )
}