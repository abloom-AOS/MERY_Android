package com.abloom.mery.categorytest

data class FakeQuestions(
    val questions: ArrayList<Question> = arrayListOf(
        Question("communication", "행복한 결혼 생황을 위해서 가장 중요한 것은 무엇이라고 생각하나요?", 1),
        Question(
            "communication",
            "부부 사이에 때로는 거짓말도 필요하다고 생각하시나용? 아니면 신회를 지키기 위해 어떠한 경우에도 거짓말을 한면 안된다고 생각하시나요?",
            10
        ),
        Question("communication", "결혼 생활에 대한 서로의 생각에 대해 얼마나 대화를 나눠본 것 같나요?", 2),
        Question("communication", "앞으로 함께 답하게 될 질문들에 얼마나 솔직하게 대답할 수 있나요?", 3),
        Question(
            "communication",
            "이야기를 나누다보면 서로 생각이 다른 부분이 분명히 있을 거예요. 그럴 때 어떻게 생각을 맞춰나갔으면 좋겠나요?",
            4
        ),

        Question("finance", "결혼하기 전에 서로의 재정 상태에 대해 투명하게 알고 있어야 한다고 생각해요?", 31),
        Question(
            "finance",
            "지금까지 모아둔 순자산은 어느 정도인가요? 예금, 적금, 투자금, 자동차, 부동산 등으로 나눠서 최대한 구체적으로 말해주세요.",
            32
        ),
        Question("finance", "최근 1년간의 한 달 평균 수입은 어떻게 되나요? 결혼 후에 수입에 변화가 있을 것 같아요?", 33),
        Question("finance", "최근 1년간의 한 달 평균 지출은 어떻게 되나요? 결혼 후에 지출에 변화가 있을 것 같아요?", 34),
        Question(
            "finance",
            "가지고 있는 빚이 있으세요? 있다면 얼마 정도인지, 왜 빚을 갖게 됐는지, 어떻게 갚아나갈 생각인지, 솔직하게 알려주세요.",
            35
        ),

        Question("lifestyle", "주말은 주로 어떻게 보내고 싶으세요?", 56),
        Question("lifestyle", "결혼 후에 새로 해보고 싶은 취미 활동이 있어요?", 61),
        Question("lifestyle", "혼자만의 시간이 필요한가요? 필요하다면 얼마나 필요한가요?", 62),
        Question("lifestyle", "혼자만의 공간이 필요한가요? 필요하다면 어떤 공간이었으면 하나요?", 63),
        Question("lifestyle", "반려동물을 기르고 싶으세요? 만약 기르고 싶다면 어떤 동물을 기르고 싶으세요?", 64),

        Question("child", "아이가 친구를 괴롭히거나 물건을 훔치는 등 나쁜 짓을 한다면 어떤 것으로 훈육하면 좋을까용?", 100),
        Question("child", "만약 두 분이 난임, 불임 등으로 인해 아이를 가지지 못하게 되는 경우에는 어떻게 하면 좋을까요?", 86),
        Question("child", "아이가 태어난 후에 육아는 어떻게 나누는 게 좋을 것 같아요?", 87),
        Question("child", "아이가 태어난 후에 집안일은 어떻게 나누는 게 좋을 것 같아요?", 88),
        Question("child", "아이의 백일잔치나 돌잔치를 하고 싶나요? 만약 한다면 어떤 식으로 하고 싶나요?", 89),

        Question("family", "내 가족들이 어떤 분들인지 상대방에게 간단하게라도 알려줄 수 있나요?", 101),
        Question("family", "가족들과의 사이는 어떠신가요? 상대방이 알아두어야 하는 가정사가 있나용?", 102),
        Question("family", "내가 가족과 상대방이 잘 지내기 위해 미리 알아두면 좋을 것들을 알려주세요.", 103),
        Question("family", "혹시라도 내 가족들과 상대방 사이의 관계에서 걱정되는 게 있다면 미리 알려주세요.", 104),
        Question("family", "내 부모님은 상대방에 대해 어떻게 생각하고 계신가요?", 105),
        Question("family", "결혼 후에 내 부모님과 관계는 어떻게 변할 것이라고 생각하나요?", 106),
        Question("family", "부모님들과는 일년에 어마나 시간을 보내게 될 것 같나요?", 107),
        Question("family", "두 분의 결혼 생활에 부모님들이 얼마나 관여했으면 좋겠나요? 너무 심하게 관여하시면 어떻게 말씀드리는게 좋을까용?", 108),
        Question("family", "상대방 부모님이랑 나 사이에 갈등이 생기면 어떻게 할거예요? 상대방은 어떻게 했으면 좋겠어요?", 109),
        Question("family", "내 부모님이랑 상대방 사이에 갈등이 생기면 어떻게 할거예요? 상대방은 어떻게 했으면 좋겠어요?", 110),

        Question("values", "결혼을 한다는 것은 당신에게 어떤 의미인가요?", 11),
        Question("values", "상대방과의 결혼을 결심한 순간은 언제였어요?", 12),
        Question("values", "결혼을 하게 되면 뭐가 가장 좋을 것 같나요?", 13),
        Question("values", "결혼 생활에서 가장 걱정되는 것은 무엇인가요?", 14),
        Question("values", "결혼으로 인해 삶에서 변할 것 같은 세가지를 무엇인가요?", 15),
        Question("values", "결혼을 하더라도 삶에서 변하지 않을 것 같은 세 가지는 무엇인가요?", 16),
        Question("values", "꿈꾸는 이상적인 결혼 생활의 모습을 한 문장으로 말해주세요.", 17),
        Question("values", "결혼 생활의 롤모델이 있나요? 있다면 누구예요? 왜 롤모델이에요?", 18),

        Question("future", "죽기 전에 꼭 하고 싶은 일이 있나요? 있다면 무엇인가요?", 161),
        Question("future", "나중에 꼭 배워보고 싶은 것이 있어요? 운동, 학문, 언어, 예술,...무엇이라도 좋아요.", 162),
        Question("future", "몇 살 때까지 일하고 싶으신가요?", 163),
        Question("future", "이직을 하거나 직업을 바꿀 계획이 있다면 알려주세요.", 164),
        Question("future", "드림타가 있나요? 있다면 어떤 차인가요?", 165),
        Question("future", "드림하우스가 있나요? 있다면 어떤 집인가요?", 166),
        Question("future", "우리나라 말고 살아보고 싶은 나라가 있나요? 있다면 어디인가요?", 167),
        Question("future", "앞으로 10년 정도 대한 계획이 있나요? 있다면 대략적으로라도 알려주세요.", 167),
        Question("future", "살다보면 분명 한 번쯤은 있을법한 고난의 시기를 부부가 함께 이겨내기 위해서는 무서이 가장 중요하다고 생각하시나요?", 169),
        Question("future", "삶의 마지막까지 함께 한다면, 주변 사람들에게 어떤 부부로 기억됐으면 좋겟나요?", 170),

        Question("present", "지금의 나 자신은 어떤 사람인지 한 문자으로 말한다면 무엇인가요?", 176),
        Question("present", "지금의 상대방과 가장 잘 어울리는 단어를 한 가지만 고른다면 어떤 단어인가요? 이유도 알려주세요.", 177),
        Question("present", "어떨 때 상대방이 가장 사랑스러워요?", 178),
        Question("present", "가끔은 상대방이 얄미울 때도 있나요? 그건 어떨 때인가요?", 179),
        Question("present", "아직까지 말한 적이 없는 비밀을 하나만 상대방에게 알려주세요.", 180),
        Question("present", "지금 가지고 계신 가장 큰 고민이 무엇인가요?", 181),
        Question("present", "제일 좋아하는 영화가 무엇인가요? 왜 그 영화를 제일 좋아하세요?", 182),
        Question("present", "평생 한 가지 음식만 먹어야 한다면 뭘 먹을거예요?", 183),
        Question("present", "최근에 가장 많이 들은 노래를 하나만 상대방에게 추천해주세요.", 184),

        Question("sex", "행복한 결혼 생활을 위해서 성관계가 얼마나 중요하다고 생각하시나요?", 121),
        Question("sex", "결혼 후 1년 동안 얼마나 자주 성관계를 하고 싶나요?", 122),
        Question("sex", "만약 상대방이 가끔씩 성관계를 거부한다면 어떨 것 같아요?", 123),
        Question("sex", "행복한 결혼 생활을 위해서 성관계가 얼마나 중요하다고 생각하시나요?", 121),
        Question("sex", "두 분이 함께했던 성관계 중에 좋았던 때는 언제인가요?", 124),
        Question("sex", "두 분이 함께했던 성관계 중에 싫었던 때는 언제인가요?", 125),
        Question("sex", "성관계할 때 '이것만은 꼭 기억해줬으면' 하는게 있어요?", 126),
        Question("sex", "성관계를 하고 싶을 때와 하고 싶지 않을 때, 상대방에게 어떤 식으로 신호를 줄거에요?", 127),
        Question("sex", "생리 기간에 성관계를 하는 것에 대해 어떻게 생각해요?", 128),
        Question("sex", "임신 기간에 성관계를 하는 것에 대해 어떻게 생각해요?", 129),

        Question("health", "예전에 격었던 신체적인 또는 정신적인 건강 문제는 어떤 것들이 있어요?", 136),
        Question("health", "지금 겪고 있는 신체적인 또는 정신적인 건강 문제는 어떤 것들이 있어요?", 137),
        Question("health", "앓고 있는 유전병이 있어요?", 138),
        Question("health", "당뇨, 심혈관질환, 치매 등에 가족력이 있어요? 이외에도 가족력이 있는 병이 있어요?", 139),
        Question("health", "예전에 격었던 신체적인 또는 정신적인 건강 문제는 어떤 것들이 있어용?", 136),
        Question("health", "건강을 위해 특별히 하고 있는 운동이 있나요?", 140),
        Question("health", "건강을 위해 특별히 챙겨먹는 음식이나 영양제 같은 것들이 있나요?", 141),
        Question("health", "건강을 위해 특별히 지키고 있는 생화습관이 있나요?", 142),
        Question("health", "가장 최근에 받은 건강검진 결과는 어땠나요?", 143),
        Question("health", "성병에 걸린 적이 있으신가용? 있다면 어쩌다가 걸리셨나요? 치료는 잘 하셨나요?", 144),

        Question("wedding", "두 분의 결혼 준비 과정에서 필수라고 생각하는 건 무엇인가요?", 146),
        Question("wedding", "결혼 준비가 어디까지 됐는지 점검해봐요. 된 것은 무엇이고,안 된 것은 무엇인 것 같나요?", 147),
        Question("wedding", "웨딩 플래너를 끼고 결혼 준비를 했으면 좋겠나요? 한다면 어디랑 하면 좋을까요?", 148),
        Question("wedding", "총 결혼 분비 비용은 어느 정도를 생각하고 계세요?", 149),
        Question("wedding", "결혼 비용은 두 분이 어떤 식으로 나누면 좋을까요? 부모님께는 어느 정도 지원 받을 수 있나요?", 150),
        Question("wedding", "원하는 결혼식의 형식이나 규모가 있나요? 비용은 얼마나 들까요?", 151),
        Question("wedding", "결혼식을 올리고 싶은 계절이 있나요? 대략이라도 생각해 놓은 날짜가 있나요?", 152),
        Question("wedding", "주례, 사회, 축가를 부탁한다면 각각 어느 분께 부탁하는 게 좋을까요? 생각해둔 분이 계신가요?", 153),
        Question("wedding", "신혼여행은 어디로 몇 박 며칠 가고 싶어요?", 154),

        Question("past", "두 분이 어떻게 처음 만나게 됐는지 기억하시나요? 그때 상대방에 대해 어떤 생각이 들었나요?", 186),
        Question("past", "두 분이 함께한 순간 중 가장 기억에 남는 순간은 언제예요?", 187),
        Question("past", "두 분이 만난 날 중 가장 좋았던 날은 언제예요?", 188),
        Question("past", "두 분이 만난 날 중 가장 싫었던 날은 언제예요?", 189),
        Question("past", "자신의 어린 시절과 가장 잘 어울리는 단어를 한 가지만 말해주세요. 왜 그 단어인가요?", 190)
    )
)
