package Kuyruklar;

import java.util.ArrayList;

import Helper.ColorWrite;

public class GorevlendiriciKuyruk {
	FcfsKuyruk fcfsKuyruk;
	RoundRobinKuyruk kuyruk1;
	RoundRobinKuyruk kuyruk2;
	RoundRobinKuyruk kuyruk3;
	RoundRobinKuyruk kuyruk4;
	RoundRobinKuyruk kuyruk5;
	RoundRobinKuyruk kuyruk6;
	RoundRobinKuyruk kuyruk7;
	RoundRobinKuyruk kuyruk8;
	ProcessItem calisanKuyruk;

	public GorevlendiriciKuyruk() {
		// TODO Auto-generated constructor stub
		fcfsKuyruk = new FcfsKuyruk(0);
		kuyruk1 = new RoundRobinKuyruk(1);
		kuyruk2 = new RoundRobinKuyruk(2);
		kuyruk3 = new RoundRobinKuyruk(3);
		kuyruk4 = new RoundRobinKuyruk(4);
		kuyruk5 = new RoundRobinKuyruk(5);
		kuyruk6 = new RoundRobinKuyruk(6);
		kuyruk7 = new RoundRobinKuyruk(7);
		kuyruk8 = new RoundRobinKuyruk(8);
	}

///Kuyrağa bir Process ekler.
	public void KuyrugaEkle(ProcessItem process) {
		switch (process.getOncelik()) {
		case 0:
			fcfsKuyruk.KuyrugaEkle(process);
			break;
		case 1:
			kuyruk1.KuyrugaEkle(process);
			break;
		case 2:
			kuyruk2.KuyrugaEkle(process);
			break;
		case 3:
			kuyruk3.KuyrugaEkle(process);
			break;
		case 4:
			kuyruk4.KuyrugaEkle(process);
			break;
		case 5:
			kuyruk5.KuyrugaEkle(process);
			break;
		case 6:
			kuyruk6.KuyrugaEkle(process);
			break;
		case 7:
			kuyruk7.KuyrugaEkle(process);
			break;
		case 8:
			kuyruk8.KuyrugaEkle(process);
			break;
		}
	}

//Kuyruktan ilgili Process Bulunup işleniyor
	public void Run(int saniye) {
		var process = GetProcess(saniye);
		if (process == null)
			return;

		if (saniye > 20) { //20 Saniye Sonra tüm görevler iptal ediliyor.
			var killList = KillProcess();

			for (var pro : killList) {
				ColorWrite.WriteProcess(pro, saniye, "öldü");
			}

			return;
		}

		if (calisanKuyruk == null || process.id != calisanKuyruk.id) {
			//Daha önce çalışan farklı process var ise askıya alınıyor
			if (calisanKuyruk != null && calisanKuyruk.kalanSure > 0
					&& calisanKuyruk.kalanSure != calisanKuyruk.processSuresi) {
				ColorWrite.WriteProcess(calisanKuyruk, saniye, "askıya alındı");
			}

			//Farklı bir processden gelen işlem ilk adımı ise başlıyor
			if (process.kalanSure == process.processSuresi) {
				ColorWrite.WriteProcess(process, saniye, "başladı");
			}
		}

		getProcessKuyruk.Isle(process);

		if (saniye < 20) {
			//Eğer işlemler ölmedi ise , kalan süresi var ise yürütülüyor
			// Fakat kalan süresi kalmadı ise sonlandırıyor
			var islemAdi = process.kalanSure > 0 ? "yürütülüyor" : "sonlandı ";
			ColorWrite.WriteProcess(process, saniye + 1, islemAdi);
		}
		calisanKuyruk = process;
	}

	Kuyruk getProcessKuyruk = null;

//İlgili saniye için kuyruk önceliklerine göre handi kuyrukta process var ise sonu getiriyor.
	ProcessItem GetProcess(int saniye) {
		var process = fcfsKuyruk.GetProcess(saniye);
		getProcessKuyruk = fcfsKuyruk;
		if (process != null)
			return process;
		process = kuyruk1.GetProcess(saniye);
		getProcessKuyruk = kuyruk1;
		if (process != null)
			return process;
		process = kuyruk2.GetProcess(saniye);
		getProcessKuyruk = kuyruk2;
		if (process != null)
			return process;
		process = kuyruk3.GetProcess(saniye);
		getProcessKuyruk = kuyruk3;
		if (process != null)
			return process;
		process = kuyruk4.GetProcess(saniye);
		getProcessKuyruk = kuyruk4;
		if (process != null)
			return process;
		process = kuyruk5.GetProcess(saniye);
		getProcessKuyruk = kuyruk5;
		if (process != null)
			return process;
		process = kuyruk6.GetProcess(saniye);
		getProcessKuyruk = kuyruk6;
		if (process != null)
			return process;
		process = kuyruk7.GetProcess(saniye);
		getProcessKuyruk = kuyruk7;
		if (process != null)
			return process;
		process = kuyruk8.GetProcess(saniye);
		getProcessKuyruk = kuyruk8;
		if (process != null)
			return process;
		getProcessKuyruk = null;
		return null;
	}
	
//Tüm ölmesi gereken process ler bir listeye ekleniyor.
	ArrayList<ProcessItem> KillProcess() {
		var liste = new ArrayList<ProcessItem>();
		liste.addAll(fcfsKuyruk.KillProcess());
		liste.addAll(kuyruk1.KillProcess());
		liste.addAll(kuyruk2.KillProcess());
		liste.addAll(kuyruk3.KillProcess());
		liste.addAll(kuyruk4.KillProcess());
		liste.addAll(kuyruk5.KillProcess());
		liste.addAll(kuyruk6.KillProcess());
		liste.addAll(kuyruk7.KillProcess());
		liste.addAll(kuyruk8.KillProcess());
		return liste;
	}

}
